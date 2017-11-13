package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.IObjectFormatter
import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.reporting.ReporterBuilder
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import ch.tutteli.atrium.spec.IAssertionVerbFactory

internal fun <T : Any> esGilt(subject: T)
    = AtriumFactory.newReportingPlant(AssertionVerb.ASSERT, subject, AtriumReporterSupplier.REPORTER)

internal fun <T : Any> esGilt(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AtriumFactory.newReportingPlantCheckLazilyAtTheEnd(AssertionVerb.ASSERT, subject, AtriumReporterSupplier.REPORTER, createAssertions)

internal fun <T : Any?> esGilt(subject: T)
    = AtriumFactory.newReportingPlantNullable(AssertionVerb.ASSERT, subject, AtriumReporterSupplier.REPORTER)

internal fun erwarte(act: () -> Unit)
    = ThrowableThrownBuilder(AssertionVerb.EXPECT_THROWN, act, AtriumReporterSupplier.REPORTER)

internal enum class AssertionVerb(override val value: String) : ISimpleTranslatable {
    ASSERT("es gilt"),
    EXPECT_THROWN("erwarte, die geworfene Exception"),
}

internal object AtriumReporterSupplier {
    val REPORTER by lazy {
        ReporterBuilder.withDetailedObjectFormatter()
            .withSameLineTextAssertionFormatter()
            .buildOnlyFailureReporter()
    }
}

/**
 * You need to add atrium-spec to your dependencies in order to be able to reuse the VerbSpec.
 */
internal object VerbSpec : ch.tutteli.atrium.spec.verbs.VerbSpec(
    "es gilt" to { subject -> esGilt(subject) },
    "es gilt" to { subject, createAssertions -> esGilt(subject, createAssertions) },
    "es gilt" to { subject -> esGilt(subject) },
    "erwarte" to { act -> erwarte { act() } })

/**
 * Only required if you implement a custom component (for instance an own [IReporter], [IObjectFormatter] etc.)
 * or an own assertion function API (e.g., atrium-assertions-code-completion in a different language)
 * and you want to reuse a specification from atrium-spec to test your custom component against it.
 */
internal object AssertionVerbFactory : IAssertionVerbFactory {
    override fun <T : Any> checkImmediately(subject: T) = esGilt(subject)
    override fun <T : Any> checkLazily(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
        = esGilt(subject, createAssertions)

    override fun <T> checkNullable(subject: T) = esGilt(subject)
    override fun checkException(act: () -> Unit) = erwarte(act)
}

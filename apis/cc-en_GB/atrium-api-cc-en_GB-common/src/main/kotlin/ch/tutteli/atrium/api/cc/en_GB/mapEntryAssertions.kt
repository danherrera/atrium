package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Makes the assertion that [Assert.subject][AssertionPlant.subject]'s [Map.Entry.key] is (equal to) the given [key] and
 * [Map.Entry.value] is [value].
 *
 * Kind of a shortcut for `key { toBe(key) }.and.value { toBe(value) }` but should be evaluated in
 * an assertion group block -- which has the effect that the assertion about the value is still evaluated even
 * if the assertion about the key fails. Moreover, it might be that reporting differs compared to using the long form.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <K : Any, V : Any> Assert<Map.Entry<K, V>>.isKeyValue(key: K, value: V): Assert<Map.Entry<K, V>>
    = addAssertion(AssertImpl.map.entry.isKeyValue(this, key, value))

/**
 * Creates an [AssertionPlant] for the [Assert.subject][AssertionPlant.subject]'s property [key][Map.Entry.key] so that further
 * fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
val <K : Any> Assert<Map.Entry<K, *>>.key get() : Assert<K> = property(Map.Entry<K, *>::key)

/**
 * Creates an [AssertionPlant] for the [Assert.subject][AssertionPlant.subject]'s property [key][Map.Entry.key] (which could be `null`)
 * so that further fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
val <K> Assert<Map.Entry<K, *>>.nullableKey get() : AssertionPlantNullable<K> = property(Map.Entry<K, *>::key)

/**
 * Makes the assertion that [Assert.subject][AssertionPlant.subject]'s property [key][Map.Entry.key] holds all assertions the given
 * [assertionCreator] might create for it.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
 */
fun <K : Any, V> Assert<Map.Entry<K, V>>.key(assertionCreator: Assert<K>.() -> Unit)
    = addAssertion(AssertImpl.map.entry.key(this, assertionCreator))

/**
 * Makes the assertion that [Assert.subject][AssertionPlant.subject]'s property [key][Map.Entry.key] (which could be `null`) holds
 * all assertions the given [assertionCreator] might create for it.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
 */
fun <K, V> Assert<Map.Entry<K, V>>.nullableKey(assertionCreator: AssertionPlantNullable<K>.() -> Unit)
    = addAssertion(AssertImpl.map.entry.nullableKey(this, assertionCreator))

/**
 * Creates an [AssertionPlant] for the [Assert.subject][AssertionPlant.subject]'s property [value][Map.Entry.value] so that further
 * fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
val <V : Any> Assert<Map.Entry<*, V>>.value get() : Assert<V> = property(Map.Entry<*, V>::value)

/**
 * Creates an [AssertionPlant] for the [Assert.subject][AssertionPlant.subject]'s property [value][Map.Entry.value]
 * (which could be `null`) so that further fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
val <V> Assert<Map.Entry<*, V>>.nullableValue get() : AssertionPlantNullable<V> = property(Map.Entry<*, V>::value)

/**
 * Makes the assertion that [Assert.subject][AssertionPlant.subject]'s property [value][Map.Entry.value] holds all assertions the given
 * [assertionCreator] might create for it.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
 */
fun <K, V: Any> Assert<Map.Entry<K, V>>.value(assertionCreator: Assert<V>.() -> Unit)
    = addAssertion(AssertImpl.map.entry.value(this, assertionCreator))

/**
 * Makes the assertion that [Assert.subject][AssertionPlant.subject]'s property [value][Map.Entry.value] holds all assertions the given
 * [assertionCreator] might create for it.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
 */
fun <K, V> Assert<Map.Entry<K, V>>.nullableValue(assertionCreator: AssertionPlantNullable<V>.() -> Unit)
    = addAssertion(AssertImpl.map.entry.nullableValue(this, assertionCreator))

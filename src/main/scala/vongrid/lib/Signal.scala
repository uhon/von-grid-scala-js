package vongrid.lib

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

/**
  * Created by uhon on 27/03/16.
  */
@js.native
@JSName("vg.Signal")
class Signal extends js.Object {
  /**
    * If Signal should keep record of previously dispatched parameters and
    * automatically execute listener during `add()`/`addOnce()` if Signal was
    * already dispatched before.
    * @property {boolean} memorize
    */
  var memorize: Boolean = js.native

  /**
    * @property {boolean} _shouldPropagate
    * @private
    */
  var _shouldPropagate: Boolean = js.native

  /**
    * If Signal is active and should broadcast events.
    * IMPORTANT: Setting this property during a dispatch will only affect the next dispatch, if you want to stop the propagation of a signal use `halt()` instead.
    * @property {boolean} active
    * @default
    */
  var active: Boolean = js.native

  /**
    * @method Signal#validateListener
    * @param {function} listener - Signal handler function.
    * @param {string} fnName - Function name.
    * @private
    */
  def validateListener(listener: js.Function2[String, js.Object, js.Any], fnName: String) = js.native

  /**
    * @method Signal#_registerListener
    * @private
    * @param {function} listener - Signal handler function.
    * @param {boolean} isOnce - Should the listener only be called once?
    * @param {object} [listenerContext] - The context under which the listener is invoked.
    * @param {number} [priority] - The priority level of the event listener. Listeners with higher priority will be executed before listeners with lower priority. Listeners with same priority level will be executed at the same order as they were added. (default = 0).
    * @return {SignalBinding} An Object representing the binding between the Signal and listener.
    */
  def _registerListener(listener: js.Function1[String, js.Object], isOnce: Boolean, listenerContext: Object, number: Float): SignalBinding = js.native

  /**
    * @method Signal#_addBinding
    * @private
    * @param {SignalBinding} binding - An Object representing the binding between the Signal and listener.
    */
  def _addBinding(binding: SignalBinding) = js.native

  /**
    * @method Signal#_indexOfListener
    * @private
    * @param {function} listener - Signal handler function.
    * @return {number} The index of the listener within the private bindings array.
    */
  def _indexOfListener(listener: js.Function1[String, js.Object], context: js.Object):Int = js.native

  /**
    * Check if listener was attached to Signal.
    *
    * @method Signal#has
    * @param {function} listener - Signal handler function.
    * @param {object} [context] - Context on which listener will be executed (object that should represent the `this` variable inside listener function).
    * @return {boolean} If Signal has the specified listener.
    */
  def has(listener: js.Function1[String, js.Object], context: js.Object):Boolean = js.native

  /**
    * Add a listener to the signal.
    *
    * @method Signal#add
    * @param {function} listener - The function to call when this Signal is dispatched.
    * @param {object} [listenerContext] - The context under which the listener will be executed (i.e. the object that should represent the `this` variable).
    * @param {number} [priority] - The priority level of the event listener. Listeners with higher priority will be executed before listeners with lower priority. Listeners with same priority level will be executed at the same order as they were added (default = 0)
    * @return {SignalBinding} An Object representing the binding between the Signal and listener.
    */

  def add(listener: js.Function2[String, js.Object, Any], listenerContext: js.Object, priority: Float = js.native): SignalBinding = js.native

  /**
    * Add listener to the signal that should be removed after first execution (will be executed only once).
    *
    * @method Signal#addOnce
    * @param {function} listener - The function to call when this Signal is dispatched.
    * @param {object} [listenerContext] - The context under which the listener will be executed (i.e. the object that should represent the `this` variable).
    * @param {number} [priority] - The priority level of the event listener. Listeners with higher priority will be executed before listeners with lower priority. Listeners with same priority level will be executed at the same order as they were added (default = 0)
    * @return {SignalBinding} An Object representing the binding between the Signal and listener.
    */
  def addOnce(listener: js.Function1[String, js.Object], listenerContext: js.Object, priority: Float): SignalBinding = js.native

  /**
    * Remove a single listener from the dispatch queue.
    *
    * @method Signal#remove
    * @param {function} listener - Handler function that should be removed.
    * @param {object} [context] - Execution context (since you can add the same handler multiple times if executing in a different context).
    * @return {function} Listener handler function.
    */
  def remove(listener: js.Function1[String, js.Object], context: js.Object): js.Function1[String, js.Object] = js.native

  /**
    * Remove all listeners from the Signal.
    *
    * @method Signal#removeAll
    * @param {object} [context=null] - If specified only listeners for the given context will be removed.
    */
  def removeAll(context: js.Object) = js.native

  /**
    * Gets the total number of listeneres attached to ths Signal.
    *
    * @method Signal#getNumListeners
    * @return {number} Number of listeners attached to the Signal.
    */
  def getNumListeners(): Int = js.native

  /**
    * Stop propagation of the event, blocking the dispatch to next listeners on the queue.
    * IMPORTANT: should be called only during signal dispatch, calling it before/after dispatch won't affect signal broadcast.
    * @see Signal.prototype.disable
    *
    * @method Signal#halt
    */
  def halt() = js.native

  /**
    * Dispatch/Broadcast Signal to all listeners added to the queue.
    *
    * @method Signal#dispatch
    * @param {any} [params] - Parameters that should be passed to each handler.
    */
  def dispatch(signal: String, objects: js.Object*) = js.native

  /**
    * Forget memorized arguments.
    * @see Signal.memorize
    *
    * @method Signal#forget
    */
  def forget() = js.native

  /**
    * Remove all bindings from signal and destroy any reference to external objects (destroy Signal object).
    * IMPORTANT: calling any method on the signal instance after calling dispose will throw errors.
    *
    * @method Signal#dispose
    */
  def dispose() = js.native

  /**
    *
    * @method Signal#toString
    * @return {string} String representation of the object.
    */
  override def toString(): String = js.native
}

@js.native
trait SignalBinding extends js.Object
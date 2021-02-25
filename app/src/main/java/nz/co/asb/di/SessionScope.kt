package nz.co.asb.di

import javax.inject.Scope

/*  Scope for session after login. All cache data and objects will be recreated with
 *  new session (if there is any).
 */
@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class SessionScope

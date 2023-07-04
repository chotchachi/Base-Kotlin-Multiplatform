package com.base.shared

import com.base.shared.di.appModule
import com.base.shared.di.databaseModule
import com.base.shared.di.networkModule
import com.base.shared.di.presentationModule
import com.base.shared.di.repositoryModule
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.ObjCObject
import kotlinx.cinterop.ObjCProtocol
import kotlinx.cinterop.getOriginalKotlinClass
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.KoinAppDeclaration

object DIContainer : KoinComponent {
    fun init(appDeclaration: KoinAppDeclaration = {}) {
        startKoin {
            appDeclaration()
            modules(
                appModule,
                networkModule,
                databaseModule,
                repositoryModule,
                presentationModule
            )
            printLogger(
                if (isDebug()) {
                    Level.DEBUG
                } else {
                    Level.ERROR
                },
            )
        }
    }

    fun get(
        type: ObjCObject,
        qualifier: Qualifier? = null,
        parameters: ParametersDefinition? = null
    ): Any? = getKoin().get(
        clazz = when (type) {
            is ObjCProtocol -> getOriginalKotlinClass(type)!!
            is ObjCClass -> getOriginalKotlinClass(type)!!
            else -> error("Cannot convert $type to KClass<*>")
        },
        qualifier = qualifier,
        parameters = parameters,
    )
}

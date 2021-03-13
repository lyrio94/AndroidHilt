package br.iesb.mobile.androidhilt.view

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

class TesteProducao: OQuePodeSerFeito {

    override fun imprimir(str: String): String {
        val x = "Olá $str!!!!!"
        println(x)
        return x
    }

}

class TesteHomologacao: OQuePodeSerFeito {

    override fun imprimir(str: String): String {
        val x = "Olá EM HOMOLOGAÇÃO $str!!!!!"
        println(x)
        return ""
    }

}


interface OQuePodeSerFeito {
    fun imprimir(str: String): String
}

@Module
@InstallIn(SingletonComponent::class)
object FabricaContratos {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Teste

    @Provides
    fun retornarContrato(): OQuePodeSerFeito {
        return TesteProducao()
    }

    @Provides
    @Homologacao
    fun retornarContratoHomologacao(): OQuePodeSerFeito {
        return TesteHomologacao()
    }

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Homologacao

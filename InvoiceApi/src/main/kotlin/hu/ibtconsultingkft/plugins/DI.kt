package hu.ibtconsultingkft.plugins

import hu.ibtconsultingkft.data.repository.InvoiceItemRepository
import hu.ibtconsultingkft.data.repository.impl.InvoiceItemRepositoryImpl
import hu.ibtconsultingkft.services.InvoiceItemService
import hu.ibtconsultingkft.services.PdfGeneratorService
import hu.ibtconsultingkft.services.impl.InvoiceItemServiceImpl
import hu.ibtconsultingkft.services.impl.PdfGeneratorServiceImpl
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureDI() {

    // Install Ktor features
    install(Koin) {
        modules(serviceModule, repositoryModule)
    }

}

val serviceModule = module {
    single<InvoiceItemService> { InvoiceItemServiceImpl(get()) }
    single<PdfGeneratorService> { PdfGeneratorServiceImpl(get()) }
}

val repositoryModule = module {
    single<InvoiceItemRepository> { InvoiceItemRepositoryImpl() }
}
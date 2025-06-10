package at.asitplus.wallet.eupidsdjwt

import at.asitplus.wallet.lib.JsonValueEncoder
import at.asitplus.wallet.lib.LibraryInitializer
import at.asitplus.wallet.lib.data.LocalDateOrInstant
import at.asitplus.wallet.lib.data.vckJsonSerializer
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.encodeToJsonElement

object Initializer {

    /**
     * A reference to this class is enough to trigger the init block
     */
    init {
        initWithVCK()
    }

    /**
     * This has to be called first, before anything first, to load the
     * relevant classes of this library into the base implementations of VC-K
     */
    fun initWithVCK() {
        LibraryInitializer.registerExtensionLibrary(
            credentialScheme = EuPidSdJwtScheme,
            jsonValueEncoder = jsonValueEncoder(),
        )
    }

    private fun jsonValueEncoder(): JsonValueEncoder = {
        when (it) {
            is IsoIec5218Gender -> vckJsonSerializer.encodeToJsonElement(it)
            is LocalDate -> vckJsonSerializer.encodeToJsonElement(it)
            is UInt -> vckJsonSerializer.encodeToJsonElement(it)
            is Instant -> vckJsonSerializer.encodeToJsonElement(it)
            is LocalDateOrInstant -> vckJsonSerializer.encodeToJsonElement(it)
            else -> null
        }
    }

}

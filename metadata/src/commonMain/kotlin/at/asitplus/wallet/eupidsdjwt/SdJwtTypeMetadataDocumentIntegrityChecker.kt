package at.asitplus.wallet.eupidsdjwt

fun interface SdJwtTypeMetadataDocumentIntegrityChecker {
    @Throws
    suspend fun checkIntegrity(
        document: SdJwtTypeMetadataDocument,
        integrityHash: SdJwtTypeMetadataIntegrityHash,
    )
}
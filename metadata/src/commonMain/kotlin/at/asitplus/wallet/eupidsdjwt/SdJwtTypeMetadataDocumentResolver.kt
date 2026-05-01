package at.asitplus.wallet.eupidsdjwt

fun interface SdJwtTypeMetadataDocumentResolver {
    /**
     * This resolves the document, ancestors, performs integrity checks and merges the inheritance tree
     */
    suspend fun resolve(
        sdJwtVcType: SdJwtVcType,
        integrityHash: SdJwtTypeMetadataIntegrityHash?,
    ): SdJwtTypeMetadata
}
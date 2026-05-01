package at.asitplus.wallet.eupidsdjwt

fun interface SdJwtTypeMetadataDocumentRetriever {
    /**
     * This resolver performs integrity checks and merges the inheritance tree
     */
    suspend fun retrieve(
        sdJwtVcType: SdJwtVcType,
    ): SdJwtTypeMetadataDocument
}
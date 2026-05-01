package at.asitplus.wallet.eupidsdjwt

data class DelegatingSdJwtTypeMetadataDocumentResolver(
    val documentRetriever: SdJwtTypeMetadataDocumentRetriever,
    val integrityChecker: SdJwtTypeMetadataDocumentIntegrityChecker,
): SdJwtTypeMetadataDocumentResolver {
    override suspend fun resolve(
        sdJwtVcType: SdJwtVcType,
        integrityHash: SdJwtTypeMetadataIntegrityHash?,
    ): SdJwtTypeMetadata {
        val visited = mutableListOf<SdJwtVcType>()
        val ancestry = mutableListOf<SdJwtTypeMetadataDocument>()
        var nextSdJwtVcType: SdJwtVcType? = sdJwtVcType
        var nextIntegrityHash = integrityHash
        while(nextSdJwtVcType != null) {
            check(nextSdJwtVcType !in visited) {
                "Expected inheritance to be non-cyclic, but was cyclic after ${visited.size} nodes, extending $nextSdJwtVcType from ${visited.last()} in $visited."
            }
            val document = documentRetriever.retrieve(nextSdJwtVcType)
            nextIntegrityHash?.let {
                require(document.definition.vct == ancestry.last().definition.extends) {
                    """Expected the extending type to specify the vct of the extended type in `extends`, but got `${ancestry.last().definition.extends}` instead of `${document.definition.vct}`."""
                }
                integrityChecker.checkIntegrity(
                    document,
                    it,
                )
            }

            ancestry.add(document)

            nextSdJwtVcType = document.definition.extends
            nextIntegrityHash = document.definition.extendsIntegrity
            visited.add(document.definition.vct)
        }

        // we already did all the checks, now we just need to merge them
        return ancestry.dropLast(1).foldRight(
            ancestry.last().definition.toSdJwtTypeMetadata()
        ) { document, acc ->
            document.definition.extend(acc)
        }
    }
}
package at.asitplus.wallet.eupidsdjwt

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = SdJwtTypeMetadataTypeDisplayInformationRenderingMetadataSvgTemplates.InlineSerializer::class)
data class SdJwtTypeMetadataTypeDisplayInformationRenderingMetadataSvgTemplates(
    private val templates: List<SvgTemplate>,
) : List<SvgTemplate> by templates {
    init {
        if (templates.size != 1) {
            templates.forEachIndexed { index, template ->
                require(template.properties != null) {
                    "Expected all templates to define properties in case there is more than 1 template, but got `null` at template index $index: `$template`"
                }
            }
        }
    }

    constructor(vararg elements: SvgTemplate) : this(elements.asList())

    class InlineSerializer : KSerializer<SdJwtTypeMetadataTypeDisplayInformationRenderingMetadataSvgTemplates> {
        private val listSerializer = ListSerializer(
            SvgTemplate.serializer(),
        )
        override val descriptor: SerialDescriptor
            get() = listSerializer.descriptor

        override fun serialize(
            encoder: Encoder,
            value: SdJwtTypeMetadataTypeDisplayInformationRenderingMetadataSvgTemplates
        ) {
            encoder.encodeSerializableValue(
                listSerializer,
                value
            )
        }

        override fun deserialize(decoder: Decoder) = SdJwtTypeMetadataTypeDisplayInformationRenderingMetadataSvgTemplates(
            decoder.decodeSerializableValue(
                listSerializer
            )
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as SdJwtTypeMetadataTypeDisplayInformationRenderingMetadataSvgTemplates

        if (templates.sortedBy { it.uri.string } != other.templates.sortedBy { it.uri.string }) return false

        return true
    }

    override fun hashCode() = templates.sortedBy { it.uri.string }.hashCode()
}
package at.asitplus.wallet.eupidsdjwt

import de.infix.testBalloon.framework.core.testSuite
import io.kotest.matchers.shouldBe
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
val SvgTemplatePropertyContrastTest by testSuite {
    /**
     * just making sure that the enum names remain consistent with the specification
     */
    test("values") {
        SvgTemplatePropertyContrast.high.name shouldBe "high"
        SvgTemplatePropertyContrast.normal.name shouldBe "normal"
    }
}
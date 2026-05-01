package at.asitplus.wallet.csp

import at.asitplus.csp.ContentSecurityPolicySourceExpressionKeywordContent
import de.infix.testBalloon.framework.core.testSuite
import io.kotest.matchers.shouldBe

val ContentSecurityPolicySourceExpressionKeywordContentTest by testSuite {
    /**
     * just making sure that the enum names remain consistent with the specification
     */
    test("values") {
        ContentSecurityPolicySourceExpressionKeywordContent.self.name shouldBe "self"
        ContentSecurityPolicySourceExpressionKeywordContent.`unsafe-inline`.name shouldBe "unsafe-inline"
        ContentSecurityPolicySourceExpressionKeywordContent.`unsafe-eval`.name shouldBe "unsafe-eval"
    }
}


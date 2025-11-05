package at.asitplus.wallet.eupidsdjwt

import de.infix.testBalloon.framework.TestSession

class ModuleTestSession : TestSession() {
    init {
        Initializer.initWithVCK()
    }
}
import SwiftUI
import ComposeApp
@main
struct iOSApp: App {
    
    init() {
        SharedUserDefaultsKt.nativeIOSResponseProvider = SharedUserDefaultsIOSBridge()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

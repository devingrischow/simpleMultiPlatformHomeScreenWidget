import SwiftUI
import ComposeApp
@main
struct iOSApp: App {
    
    init() {
        SharedUserDefaultsKt.nativeIOSResponseProvider = SharedUserDefaultsIOSBridge()
        
        WidgetHandling_iosKt.iOSNativeResponseProvider = WidgetHandlingIOSBridge()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
               .onOpenURL { url in

                   //For Test get URL from
                   let launchedStringUrl = url.absoluteString

                   print("Launched from URL: \(launchedStringUrl)")

                   WidgetAppleLaunchURLHandler.shared.launchedWithURL = launchedStringUrl

                   // AppleReloadShared.companion.onAppOpenedFromWidget()

               }
                
        }
    }
}


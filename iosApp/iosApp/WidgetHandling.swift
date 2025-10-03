//
//  WidgetHandling.swift
//  iosApp
//
//  Created by Devin Grischow on 10/2/25.
//

import Foundation
import ComposeApp


class WidgetHandlingIOSBridge: IOSWidgetResponseProvider {
    func getDidLaunchFromWidget() -> String? {
        print("Returning URL: \(WidgetAppleLaunchURLHandler.shared.launchedWithURL)")
        return WidgetAppleLaunchURLHandler.shared.launchedWithURL
    }


}


//Static Data Holder for On Launch Application
class WidgetAppleLaunchURLHandler {
    static var shared = WidgetAppleLaunchURLHandler()
    
    var launchedWithURL:String? = nil
    
}

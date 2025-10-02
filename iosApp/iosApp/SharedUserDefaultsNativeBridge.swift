//
// Created by Devin Grischow on 10/1/25.
//

import Foundation
import ComposeApp
import WidgetKit

class SharedUserDefaultsIOSBridge: IOSResponseProvider {
    func getCountFromUserPreference(collectionID: String, valueKey: String) -> Int32 {
        let prefs = UserDefaults.init(suiteName: collectionID)
        let counterValue = prefs?.integer(forKey: valueKey) ?? 0

        return Int32(counterValue)
    }

    func setCountToUserPreference(collectionID: String, valueKey: String, newValue: Int32) {
        let prefs = UserDefaults.init(suiteName: collectionID)

        print("Setting New Count: \(newValue) \n")
        let newInt = Int(newValue)

        prefs?.set(newInt, forKey: valueKey)
        

        WidgetCenter.shared.reloadAllTimelines()
    }

    func getNativeResponse() -> String {
        return "Hello from Swift!"
    }
    

    
     
    
    
    
}

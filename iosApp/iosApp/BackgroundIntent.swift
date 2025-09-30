//
//  BackgroundIntent.swift
//  iosApp
//
//  Created by Devin Grischow on 9/30/25.
//

import Foundation
import AppIntents
import ComposeApp
import WidgetKit


private let widgetGroupId = "group.example.widget_group"
private let countingKey = "count";


@available(iOS 17, *)
public struct BackgroundIntent: AppIntent {
    static public var title: LocalizedStringResource = "Simple Widgets Background Intent"
    
    @Parameter(title: "count")
    var count: Int?
    
    public init() {}
    

    public init(count:Int?) {
        self.count = count
    }
    
    
    public func perform() async throws -> some IntentResult {
        let prefs = UserDefaults.init(suiteName: widgetGroupId)

        print("Preforming Background Action")
        let c = CounterHandler()
        
        //increment count
        let counterValue = prefs?.integer(forKey: countingKey) ?? 0

        let newCount = c.incrementValue(currVal: Int32(counterValue))
        
        prefs?.set(newCount, forKey: countingKey)
        
        WidgetCenter.shared.reloadAllTimelines()


        return .result()
    }
    
}



@available(iOS 17, *)
@available(iOSApplicationExtension, unavailable)
extension BackgroundIntent: ForegroundContinuableIntent {}

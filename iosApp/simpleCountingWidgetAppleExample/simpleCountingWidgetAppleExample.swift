//
//  simpleCountingWidgetAppleExample.swift
//  simpleCountingWidgetAppleExample
//
//  Created by Devin Grischow on 9/30/25.
//

import WidgetKit
import SwiftUI
import ComposeApp


private let widgetGroupId = "group.example.widget_group"

struct Provider: TimelineProvider {
    func placeholder(in context: Context) -> SimpleEntry {
        SimpleEntry(date: Date(), count: 0)
    }
    
    

    func getSnapshot(in context: Context, completion: @escaping (SimpleEntry) -> ()) {
        let prefs = UserDefaults.init(suiteName: widgetGroupId)
        let counterValue = prefs?.integer(forKey: CounterHandler().countKey) ?? 0
        
        print("Get Snapshot Counter Value: \(counterValue) \n")
        
        let entry = SimpleEntry(date: Date(), count: counterValue)
        completion(entry)
    }

    func getTimeline(in context: Context, completion: @escaping (Timeline<Entry>) -> ()) {
        
        getSnapshot(in: context) { (entry) in
            let timeline = Timeline(entries: [entry], policy: .atEnd)
            completion(timeline)
        }
        
    }

//    func relevances() async -> WidgetRelevances<Void> {
//        // Generate a list containing the contexts this widget is relevant in.
//    }
}

struct SimpleEntry: TimelineEntry {
    let date: Date
    let count: Int
}

struct simpleCountingWidgetAppleExampleEntryView : View {
    var entry: Provider.Entry

    var body: some View {
        VStack {
            Text("Time:")
            Text(entry.date, style: .time)
            Text("Count:")
            Text("\(entry.count)")
            
            if #available(iOSApplicationExtension 17, *){
                Button(intent: BackgroundIntent()){
                    Text("+")
                }
            }
           
            
            
        }
    }
}

struct simpleCountingWidgetAppleExample: Widget {
    let kind: String = "simpleCountingWidgetAppleExample"

    var body: some WidgetConfiguration {
        StaticConfiguration(kind: kind, provider: Provider()) { entry in
            if #available(iOS 17.0, *) {
                simpleCountingWidgetAppleExampleEntryView(entry: entry)
                    .containerBackground(.fill.tertiary, for: .widget)
            } else {
                simpleCountingWidgetAppleExampleEntryView(entry: entry)
                    .padding()
                    .background()
            }
        }
        .configurationDisplayName("My Widget")
        .description("This is an example widget.")
    }
}

#Preview(as: .systemSmall) {
    simpleCountingWidgetAppleExample()
} timeline: {
    SimpleEntry(date: .now, count: 0)
    SimpleEntry(date: .now, count: 0)
}

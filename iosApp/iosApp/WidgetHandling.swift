//
//  WidgetHandling.swift
//  iosApp
//
//  Created by Devin Grischow on 10/2/25.
//

import Foundation
import ComposeApp
import WidgetKit


class WidgetHandlingIOSBridge: IOSWidgetResponseProvider {
    func getWidgetsInfoString(result: @escaping () -> Void) {


        WidgetCenter.shared.getCurrentConfigurations { widgetResult in
            //Switch on widget result action
            switch widgetResult {
                //Success means there is any widgets
            case .success(let widgets):
                print("Widget Success")
                let widgetInfoList:[String] = widgets.map { widgetInfo in

                    //Simple Return Widget Info strings
                    let widgetFamilyString = widgetInfo.family.description

                    let widgetKindString = widgetInfo.kind.description

                    let widgetInfoString:String = "Family: \(widgetFamilyString) Kind: \(widgetKindString)"

                    print("To set Widget Info String: \(widgetInfoString)")

                    return widgetInfoString



                }

                print("Widget Info List: \(widgetInfoList)")


                WidgetAppleLaunchURLHandler.shared.availableWidgets = widgetInfoList

                result()

            case .failure(let error):
                //no widgets found, Error
                print("Failed to get widgets: \(error.localizedDescription)")
            }

        }

        result()

    }

    func getWidgetsInfo() -> [String]? {
        var r:[String]? = nil

        getWidgetsInfoString{
            r = WidgetAppleLaunchURLHandler.shared.availableWidgets
        }


        print("widgets Return Value Being Returned: \(r)")

        return r



    }

    func getDidLaunchFromWidget() -> String? {
        print("Returning URL: \(WidgetAppleLaunchURLHandler.shared.launchedWithURL)")
        return WidgetAppleLaunchURLHandler.shared.launchedWithURL
    }
    
    


}


//Static Data Holder for On Launch Application
class WidgetAppleLaunchURLHandler {
    static var shared = WidgetAppleLaunchURLHandler()
    
    var launchedWithURL:String? = nil

    var availableWidgets:[String]? = nil
    
}

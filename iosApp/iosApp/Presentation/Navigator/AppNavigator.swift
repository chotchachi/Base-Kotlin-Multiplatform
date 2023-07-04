//
//  AppNavigator.swift
//  iosApp
//
//  Created by Thanh Quang on 03/07/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import UIKit

final class AppNavigator {
    private var window: UIWindow? = nil
    
    func setRootViewController(window: UIWindow) {
        self.window = window
        let vc = SplashViewController()
        vc.navigator = self
        window.rootViewController = vc
        window.makeKeyAndVisible()
    }
    
    func toMainNavigation() {
//        let mainTabBarController = MainTabBarController(
//            navigator: self,
//            appDIContainer: appDIContainer,
//            billingManager: .shared,
//            initialTabBarIndex: Application.shared.initialTabBarIndex
//        )
//        window?.rootViewController = MainNavigationController(rootViewController: mainTabBarController)
//        window?.makeKeyAndVisible()
    }
    
    
}

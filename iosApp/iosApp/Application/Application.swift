//
//  Application.swift
//  iosApp
//
//  Created by Thanh Quang on 03/07/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import UIKit

final class Application: NSObject {
    static let shared = Application()
    
    private var window: UIWindow?
    private let navigator: AppNavigator
//    private let billingManager: BillingManager
    
    // Ads Manager
//    private let appOpenAdMananger = AppOpenAdManager.shared
//    private let interstitialAdManager = InterstitialAdManager.shared
//    private let rewardedAdManager = RewardedAdManager.shared

    var isOpenInApp = false
    var isSetupAndShowAdFinish = false
    var initialTabBarIndex = 0

    override init() {
        navigator = AppNavigator()
//        billingManager = BillingManager.shared
        super.init()
        
//        appOpenAdMananger.delegate = self
//        appOpenAdMananger.appOpenAdManagerDelegate = self
//
//        interstitialAdManager.delegate = self
//        interstitialAdManager.createAndLoadAdvertisement()
//
//        rewardedAdManager.delegate = self
//        rewardedAdManager.loadRewardedAd()
    }
    
    func configureMainInterface(in window: UIWindow) {
        self.window = window
        UIView.transition(with: window, duration: 0.3, options: .transitionFlipFromLeft, animations: { [weak self] in
            self?.navigator.setRootViewController(window: window)
        }, completion: nil)
    }
    
    /// Show app open ads
    func showAppOpenAdIfAvailable() {
        guard let vc = window?.rootViewController, !isOpenInApp, isSetupAndShowAdFinish else { return }
//        appOpenAdMananger.showAppOpenAdIfAvailable(vc)
    }
}

//
//  MainNavigationController.swift
//  iosApp
//
//  Created by Thanh Quang on 03/07/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import UIKit

class MainNavigationController: UINavigationController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setNavigationBarHidden(true, animated: false)
        interactivePopGestureRecognizer?.delegate = self
    }
    
}

// MARK: - UIGestureRecognizerDelegate

extension MainNavigationController: UIGestureRecognizerDelegate {
    func gestureRecognizerShouldBegin(_ gestureRecognizer: UIGestureRecognizer) -> Bool {
        return viewControllers.count > 1
    }
}

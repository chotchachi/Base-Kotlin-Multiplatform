//
//  DIContainer+get.swift
//  iosApp
//
//  Created by Thanh Quang on 03/07/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension DIContainer {
    func get<T>(
        for type: T.Type = T.self,
        qualifier: Koin_coreQualifier? = nil,
        parameters: (() -> Koin_coreParametersHolder)? = nil
    ) -> T {
        self.get(
            type: type,
            qualifier: qualifier,
            parameters: parameters
        ) as! T
    }
}

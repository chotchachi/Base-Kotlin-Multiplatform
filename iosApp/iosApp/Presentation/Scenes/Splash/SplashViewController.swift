//
//  SplashViewController.swift
//  iosApp
//
//  Created by Thanh Quang on 03/07/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import UIKit
import shared
import KMPNativeCoroutinesRxSwift
import RxSwift

class SplashViewController: UIViewController, Navigatable {

    // MARK: - Outlets
    
    // MARK: - Properties
    
    var navigator: AppNavigator!
    private let viewModel: MainViewModel = DIContainer.shared.get()
    private let bag = DisposeBag()
    
    // MARK: - Lifecycle

    override func viewDidLoad() {
        super.viewDidLoad()
                
        createObservable(for: viewModel.stateFlow)
            .subscribe(onNext: {
                print($0)
            })
            .disposed(by: bag)
        
        DispatchQueue.main.asyncAfter(deadline: .now()+3) {
            self.load()
        }
        
    }

    private func load() {
        if let image = UIImage(named: "my_picture1")?.pngData() {
            viewModel.processIntent(intent: MainIntent.GetImageID(image: KotlinByteArray.from(data: image)))
        }
    }
}

extension KotlinByteArray {
    static func from(data: Data) -> KotlinByteArray {
        let swiftByteArray = [UInt8](data)
        return swiftByteArray
            .map(Int8.init(bitPattern:))
            .enumerated()
            .reduce(into: KotlinByteArray(size: Int32(swiftByteArray.count))) { result, row in
                result.set(index: Int32(row.offset), value: row.element)
            }
    }
}

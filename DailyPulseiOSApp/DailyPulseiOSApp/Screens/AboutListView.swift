//
//  AboutListView.swift
//  DailyPulseiOSApp
//
//  Created by Amit Roy on 19/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import DailyPulseShared

struct AboutListView: View {
    
    private struct RowItem: Hashable {
        let title: String
        let subtitle: String
    }
    
    private let items: [RowItem] = {
        let platform = Platform_iosKt.getPlatform()
        
        var result: [RowItem] = [
            .init(
                title: "Operating System",
                subtitle: "\(platform.osName) - \(platform.osVersion)"
            ),
            .init(title: "Device", subtitle: platform.deviceModel),
            .init(title: "Density", subtitle: "@\(platform.density)x")
        ]
        return result
    }()
    
    var body: some View {
        List {
            ForEach(items, id: \.self) { item in
                VStack(alignment: .leading) {
                    Text(item.title)
                        .font(.footnote)
                        .foregroundStyle(.secondary)
                    Text(item.subtitle)
                        .font(.body)
                        .foregroundStyle(.primary)
                }.padding(.vertical, 4)
            }
        }
    }
}

#Preview {
    AboutListView()
}

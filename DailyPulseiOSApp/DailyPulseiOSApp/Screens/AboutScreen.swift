//
//  AboutScreen.swift
//  DailyPulseiOSApp
//
//  Created by Amit Roy on 20/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct AboutScreen: View {
    var body: some View {
        NavigationStack {
            AboutListView()
                .navigationTitle("About Screen")
        }
    }
}

#Preview {
    AboutScreen()
}

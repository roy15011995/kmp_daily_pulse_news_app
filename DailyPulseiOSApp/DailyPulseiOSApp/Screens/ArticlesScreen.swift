//
//  ArticlesScreen.swift
//  DailyPulseiOSApp
//
//  Created by Amit Roy on 20/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import DailyPulseShared

extension ArticlesScreen {
    
    @MainActor
    class ArticlesViewModelWrapper: ObservableObject {
        let articlesViewModel: ArticlesViewModel
        @Published var articlesState: ArticlesState
        init() {
            articlesViewModel = ArticlesViewModel()
            articlesState = ArticlesState(articles: [] ,loading: false, error: nil)
        }
        
        func startObserving() {
            Task {
                for await articleState in articlesViewModel.{
                    self.articlesState = articleState as! ArticlesState
                }
            }
        }
    }
}

struct ArticlesScreen: View {
    @ObservedObject private(set) var viewModel: ArticlesViewModelWrapper
    
    var body: some View {
        VStack {
            AppBar()
            
            if viewModel.articlesState.loading {
                Loader()
            }
            
            if let errorMessage = viewModel.articlesState.error {
                ErrorMessage(message: errorMessage)
            }
            
            if (!viewModel.articlesState.articles.isEmpty) {
                ScrollView {
                    LazyVStack(spacing: 10) {
                        ForEach(viewModel.articlesState.articles, id: \.self) { article in
                            ArticleItemView(article: article)
                        }
                    }
                }
            }
        }.onAppear {
            self.viewModel.startObserving()
        }
    }
}

struct AppBar: View {
    var body: some View {
        Text("Articles")
            .font(.largeTitle)
            .fontWeight(.bold)
    }
}

struct Loader: View {
    var body: some View {
        ProgressView()
    }
}

struct ErrorMessage: View {
    var message: String
    var body: some View {
        Text(message)
            .font(.title)
    }
}

struct ArticleItemView: View {
    var article: Article
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: article.imageUrl)) { phase in
                if phase.image != nil {
                    phase.image!
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Image loading failed")
                } else {
                    ProgressView()
                }
            }
            Text(article.title)
                .font(.title)
                .fontWeight(.bold)
            Text(article.desc)
            Text(article.date).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }.padding(16)
    }
}

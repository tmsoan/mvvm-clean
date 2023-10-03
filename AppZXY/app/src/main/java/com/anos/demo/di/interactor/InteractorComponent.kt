package com.anos.demo.di.interactor

import com.anos.demo.di.UserScope
import com.anos.demo.di.repository.RepositoryComponent
import com.anos.demo.di.repository.RepositoryComponentHolder
import com.anos.demo.view.list.NewsListViewModel
import dagger.Component

@UserScope
@Component(
    dependencies = [RepositoryComponent::class],
    modules = [InteractorModule::class]
)
interface InteractorComponent {
    fun inject(app: NewsListViewModel)
}

object InteractorComponentHolder {
    fun get(): InteractorComponent {
        return DaggerInteractorComponent.builder()
            .repositoryComponent(RepositoryComponentHolder.get())
            .interactorModule(InteractorModule())
            .build()
    }
}
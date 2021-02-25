package nz.co.asb.di

import dagger.Component

@SessionScope
@Component(
    dependencies = [AppSingletonComponent::class]
)
interface AppSessionComponent {
}

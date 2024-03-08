package com.example.myapplication

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.metamask.androidsdk.*


@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

     @Provides
         fun provideDappMetadata(@ApplicationContext context: Context): DappMetadata {
             return DappMetadata(
                 name = context.applicationInfo.name,
                 url = "https://${context.applicationInfo.name}.com",
                 iconUrl = "https://cdn.sstatic.net/Sites/stackoverflow/Img/apple-touch-icon.png"
             )
         }

     @Provides
         fun provideEthereumFlow(@ApplicationContext context: Context, dappMetadata: DappMetadata): EthereumFlow {
             return EthereumFlow(
                 Ethereum(
                     context,
                     dappMetadata,
                     SDKOptions(infuraAPIKey = BuildConfig.MY_INFURA_KEY)
                 )
             )
         }
}
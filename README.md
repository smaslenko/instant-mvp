[ ![Download](https://api.bintray.com/packages/ispstas/stonemas/instant-mvp/images/download.svg) ](https://bintray.com/ispstas/stonemas/instant-mvp/_latestVersion)

![](https://github.com/smaslenko/instant-mvp/workflows/Android%20CI/badge.svg)

# instant-mvp
Lightweight Android MVP library that binds your Activity or Fragment to the model-view-presenter trio.  

It consists of several main classes that have to be inharited in order to make library working:  
* `MvpActivity`
* `MvpFragmment`
* `MvpModel`
* `MvpView`
* `MvpPresenter`


`MvpModel` is backed by `ViewModel` from **Android Architecture Components** .
 
`MvpView` is an interface that your `MvpActivity` or `MvpFragmment` should implement . 

`MvpPresenter` is a middleman that's coupled with both of `View` and `Model` (`View` is a `WeakReference` that gets cleared in `onDestroy` for Activity and in `onDetach()` for Fragment). It is also lifecycle-aware, so there are lifecycle callbacks accessible to override:  

* `onCreate()`
* `onResume()`
* `onStart()`
* `onPause()`
* `onStop()`
* `onDestroy()`


## Usage
(Besides the info below, you can find more details in the sample app)

#### Activity

```
class SampleActivity : MvpActivity<SampleModelA, SampleViewA, SamplePresenterA>(), SampleViewA {...}
```
With `Activity`, the trio is fully bound right during `onCreate(savedInstanceState: Bundle?)` invocation, and corresponding `MvpPresenter`'s `onCreate()` fired.


#### Fragment
```
class SampleFragment : MvpFragment<SampleModelF, SampleViewF, SamplePresenterF>(), SampleViewF {...}
```
With `Fragment`, the trio is fully bound right during `onActivityCreated(savedInstanceState: Bundle?)` invocation, and corresponding `MvpPresenter`'s `onCreate()` fired.

Note: within current implementation, `MvpFragment` is an descender of `BottomSheetDialogFragment` (to be re-worked to support arbitrary Fragment type in future releases), and can be shown simply by 

```
SampleFragment().show(supportFragmentManager, null)
```

To control its expanded/collapsed state, just override `expandedOnStart` boolean value (set to `true` by default) in your Fragment:

```
override val expandedOnStart = false // true by default
```

To switch between expanded/collapsed states at any time during the fragment visible lifespan, just invoke:
```
expandFragment(expand: Boolean)
```

#### LiveData
Since every `MvpPresenter` is lifecycle-aware, it has `lifecycleOwner` property object that can be used to observe `LiveData` produced by the `MvpModel`. In the **instant-mvp**, there is a helper function `observe(data, observer)` that simplifies coding efforts. Example:
```
class SamplePresenterA : MvpPresenter<SampleModelA, SampleViewA>() {
   override fun onCreate() {
        super.onCreate() // Not required

        observe(model.someLiveData, Observer<SomeType> { someTypeValue ->
            view?.doSomethingWith(someTypeValue)
        })
    }
}    
```



## Installation
Add this in your app's **build.gradle** file:

```
compile 'com.stonemas.instantmvp:instant-mvp:0.1.2'
```

## License
MIT License

Copyright (c) 2019 Stanislav Maslenko

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

# StompingGrounds
this is a repo to test things out, each branch is a thing i want to test about

## mvp
this is a branch to test my mvp idea. currently it has the bare bones of an android app using mvp architecture

### architecture
basic mvp with the main thrust being the app will be made up of activities and views. each activity 
will be composed of a number of android view (each with a corresponding MvpView and Presenter).

#### MvpView
when you write a new activity, the first thing you do is write the xml view, using `<include>` tags for each component shown. when you inflate the activity view, you call `findViewById()` on each `<include>` tag's id. next you instantiate a subclass of `BaseMvpView` for the returned android view. `BaseMvpView` works similarly to `ViewHolder` from `RecyclerView`. the default constructor simple saves the view and injects any butterknife `@Bind` annotations. these subclasses are intended to implement extensions of the `MvpView` interface and be passed to subclasses of `BasePresenter` in `attachView(MvpView view)`. (note: this viewholder patter is not necessary, you could have your `Activity` implement the `MvpView` interface if you wanted.)

#### BasePresenter
the next layer of architecture are the presenters. these are intended to be android agnostic. they have two public methods by default: `attachView()`, which accepts an `MvpView` and `detachView()` which sets the internal view reference to `null`. if the presenter tries to call methods specified in its `MvpView` before `attachView()` is called, a `RunTimeException` will be thrown. `attachView()` is intended to be called after android views are inflated and paired with the corrosponding `detachView()` call to avoid memory leaks caused by holding inappropriate reference to android view components.

#### implementations
writing interfaces is the first step. extend `MvpView` and `MvpPresenter` to define a contract for subsequent interactions. `BasePresenter`s will have access to the view only through the contract provided in `attachView()`. access to the presenter from the `MvpView` should be done by writing an interface which the subclass of `BasePresenter` will implement.

implementing these contracts is the next step. `MvpView` can be implemented in any standard lifecycle-aware android component (`AppCompatActivity`, `Fragment`, even `ViewGroup` subclasses) or by subclasses of `BaseMvpView`, which acts as a `ViewHolder`. implementations of the presenter contract should inherit from `BasePresenter`. 

it is critical to the architecture that `MvpView`s do not contain any business logic, which should all be encapsulated in a subclass of `MvpPresenter`. `MvpView`s should merely react to messages from the `MvpPresenter` and forward ui events (clicks, text change events, focus gained/lost, etc.) to the `MvpPresenter` through an interface implemented by the `MvpPresenter`. 

#### testing
testing should be done "presenter first". first, all test cases should be listed. each `MvpPresenter` should get a test class annotated with `@RunWith(MockitoJUnitRunner.class)` in order to use `Mockito` to mock the `MvpView` interactions. each listed test case should be written as a failing test (ie `Assert.assertTrue(false, true)`) and red/green cycles used to iterate through the list and fill in the business logic.

next um, Ui tests using `Espresso` can be written? or we can just ... um visually run through them to make sure they work with the android view components. these tests will run on the device and are cool and awesome but i dont know anything about them.

#### RxJava testing gotcha
work done off the main thread (primarily network and database calls) should be done using RxJava. care should be taken to ensure the correct threads are used to subscribe to observables and observe events. typically `Schedulers.io()` and `AndroidSchedulers.mainThread()` should be used. however, this presents a gotcha when doing testing. because the unit tests will be run in the jvm, agnostic of the android framework, using `AndroidSchedulers.mainThread()` to observe events will cause the tests to fail. for this reason, subclasses of `BasePresenter` should provide a constructor to inject an `Observable.Transformer`. In application code, `RxSchedulerApplier.SUBSCRIBE_IO_OBSERVE_ANDROID_MAIN` is provided as a default `Observable.Transformer`. in testing code, a custom implementation should be injected into `BasePresenter`s under test. this implementation should subscribe and observe using `Scheduler.immediate()`. `.compose()` should be used to apply the `Observable.Transformer`.
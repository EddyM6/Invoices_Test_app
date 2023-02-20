# Invoices_Test_app

About
This android application that shows a list of invoices for a set of invoices parsed from the provided JSON endpoint. Each invoice can have multiple line items and it should reflect total amount per invoice. 

Permissions
•	Full Network Access.
•	View Network Connections.
In-App functionality
•	Show All Invoices Info.
•	Show Invoice Info Details.
•	Show Malformed Data Screen.
•	Show Empty Data Screen.
•	Show Error Screen.
List of APIs used
•	Base Url: https://storage.googleapis.com/
•	Success data endpoint: xmm-homework/invoices.json
•	Malformed data endpoint: xmm-homework/invoices_malformed.json
•	Empty data endpoint: xmm-homework/invoices_empty.json
Activities started within the UI:
•	Splash Screen: first start
•	Invoice Info screen : show success invoice info data
•	Invoice Info Details screen: show details info for this invoice
Libraries used
•	Jetpack compose (ver.-1.2.0) -  modern UI toolkit with state driven development
•	Koin (DI) (ver.-3.1.5) – I used that because it faster to setup for small project and easier to use ( rather than Hilt, Dagger 2)
•	Coroutines (ver.-1.6.4) – choose coroutines because project is written in kotlin and uses all kotlin syntax power, and has better performance than RxJava out of the box
•	Orbit(MVI Architecture)(ver.-4.3.2) – it easier and faster to setup rather than to build it from scrutch, alsoit is MVI and it better works for jetpack compose
•	Retrofit (ver.-2.9.0)
•	Lottie Anim (ver.-4.0.0) – to make application looks a little bit better))
•	Mockito (Unit Test ver.-4.1.0) – also for time perspective easier and faster to setup rather than Kotest


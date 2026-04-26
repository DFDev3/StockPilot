# StockPilot MVP

StockPilot is a simple Android MVP for product catalog management.

It includes:

- a login screen
- a product list with image, name, and price
- a product detail screen
- a product creation form (with Firestore save intent)

## Project Goal

This project is designed as a starter MVP to validate a basic inventory/catalog flow before scaling into a full production app.

## Tech Stack

- Java (Android)
- AndroidX + Material Components
- RecyclerView
- Picasso (image loading)
- Firebase Firestore SDK (for product persistence)
- Gradle (Groovy)

## Current Features

- Session-based login using SharedPreferences
- Seed/sample product catalog
- Product detail navigation
- Product remove action from list UI
- Product form with input validation

## Suggested Test Credentials

- Username: `Fabian`
- Password: `123456`

## Project Structure

```text
app/src/main/java/com/example/stockpilot/
  model/
    Product.java
  ui/
    LoginActivity.java
    ProductListActivity.java
    ProductDetailActivity.java
    ProductFormActivity.java
    adapter/
      ProductAdapter.java
```

## Build & Run

1. Open the project in Android Studio.
2. Sync Gradle.
3. Run on emulator/device (API 23+).

From terminal:

```bash
./gradlew assembleDebug
```

On Windows PowerShell:

```powershell
.\gradlew.bat assembleDebug
```

## Firebase Notes

- The project includes `google-services.json` as a placeholder for Firebase setup.
- If you want to fully enable Firestore in your environment, make sure your Firebase app configuration matches the current package name `com.example.stockpilot`.

## MVP Backlog (Next Steps)

- Persist and reload products from Firestore in the list screen
- Add loading and error states
- Add input masks and stricter URL validation
- Introduce ViewModel + Repository separation
- Add unit/instrumented tests for auth and product flows

## License

No license file is currently defined.

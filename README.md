# My Mobile Full-Stack Learning Project (Android + FastAPI)

This repo is my first end-to-end “mobile full stack” project:
- **Backend:** Python + FastAPI (local API server)
- **Frontend:** Android (Jetpack Compose)
- **Goal:** Learn the fundamentals of mobile backend development (APIs, Experience APIs/xAPI-style endpoints, networking) and then extend this project into AI classification → agents → chatbot/orchestration.

---

## ✅ What’s done so far (Up to Part 3)

### Backend (FastAPI)
- Created a FastAPI server locally
- Built an “Experience API”-style endpoint:
    - `GET /v1/experience/home` → returns a JSON payload shaped for the mobile UI (greeting + list of cards)
- Added a health endpoint:
    - `GET /health`

### Android (Compose)
- Created an Android app using Jetpack Compose
- Added networking using Retrofit + OkHttp
- Called the backend from the Android emulator using:
    - Base URL: `http://10.0.2.2:8000/`
- Fixed Android cleartext networking issue for local HTTP development
- Rendered the response cleanly in Compose using a scrollable list + Material3 cards

### GitHub
- Monorepo structure with `backend/` and `android/`
- Code committed and pushed to GitHub

---

## Repo Structure
my-mobile-fullstack/
backend/ # Python FastAPI backend
android/ # Android Studio project (Compose app)
README.md


---

## Backend Setup (Local)

### Prerequisites
- Python 3.x installed
- macOS (tested on my Mac)

### Create virtualenv + install dependencies
```bash
cd backend
python3 -m venv .venv
source .venv/bin/activate
pip install fastapi uvicorn pydantic
```

#### Run the server
```bash
uvicorn main:app --reload --port 8000
```

### Test endpoints

Health check:
http://127.0.0.1:8000/health

Experience API endpoint:
http://127.0.0.1:8000/v1/experience/home

Swagger docs:
http://127.0.0.1:8000/docs

---

## Android Setup (Compose App)
 - Emulator networking note

- When backend runs on my laptop as http://127.0.0.1:8000,
the Android emulator accesses the host machine via:

✅ http://10.0.2.2:8000/

- So Retrofit base URL is: 
private const val BASE_URL = "http://10.0.2.2:8000/"

#### Cleartext HTTP fix (local dev)

Android blocks HTTP by default, so local testing required enabling cleartext traffic.

In AndroidManifest.xml inside <application>:
````
<application
android:usesCleartextTraffic="true"
... >
````
(Later, once deployed with HTTPS, this won’t be needed.)

---
### Screenshots

- [screenshots/backend_swagger.png](https://github.com/ManaswiniKundeti/my-mobile-fullstack/blob/main/screenshots/backend_swagger.png)
- [screenshots/backend_home.png](https://github.com/ManaswiniKundeti/my-mobile-fullstack/blob/main/screenshots/backend_home.png)
- [screenshots/android_ui.png](https://github.com/ManaswiniKundeti/my-mobile-fullstack/blob/main/screenshots/android_ui.png)

---

## Known Issues / Notes

- Initially hit a FastAPI 500 due to forgetting to return the response model.

Fix: return HomeExperienceResponse(...)

- Android UI overlapped under top bars until Scaffold padding was applied

Fix: apply Modifier.padding(innerPadding) + screen padding

---

## Commands used often

### Backend
````
cd backend
source .venv/bin/activate
uvicorn main:app --reload --port 8000
````
### Git
````
git status
git add .
git commit -m "message"
git push
````

---

#### Why I built this

I’m an Android developer learning backend development from scratch to become capable of building:

- mobile + backend end-to-end systems
- AI-powered mobile apps










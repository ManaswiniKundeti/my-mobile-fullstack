from fastapi import FastAPI
from pydantic import BaseModel
from typing import List

app = FastAPI(title="Mobile Backend", version="1.0.0")

# ----- Experience API response models -----
class Card(BaseModel):
    title: str
    subtitle: str

class HomeExperienceResponse(BaseModel):
    greeting: str
    cards: List[Card]



# ----- Experience API (xAPI style) -----
@app.get("/v1/experience/home", response_model=HomeExperienceResponse)
def get_home_experience():
    # In a real system this would aggregate multiple internal services.
    return HomeExperienceResponse(
        greeting="Hello from your first backend",
        cards=[
            Card(title="Backend", subtitle="FastAPI running locally"),
            Card(title="Next", subtitle="Android app calls this endpoint"),
            Card(title="Later", subtitle="Add classify and agent tools"),
        ],
    )


@app.get("/health")
def health():
    return {"ok": True}

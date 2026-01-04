import httpClient from "./httpClient"

export const parkingLots = async ()=>{
    return httpClient.get("/api/parking-lots")
}
export const reserveParkingSlot = async (parkingLotId)=>{
    return httpClient.post("/api/reservations",{"parkingLotId": parkingLotId})
}
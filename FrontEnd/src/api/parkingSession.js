import httpClient from "./httpClient"

export const startParkingSession = (reservationId)=>{
    console.log("reservation id: ", reservationId)
    return httpClient.post(`/api/parking-sessions/start/${reservationId}`);
}
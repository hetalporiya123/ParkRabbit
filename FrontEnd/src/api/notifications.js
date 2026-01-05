import httpClient from "./httpClient"

export const getAllNotifications = async()=>{
    return httpClient.get("/api/notifications")
}
export const updateNotificationStatus = async(id)=>{
    console.log("HTTP client: ", id)
    return httpClient.patch(`/api/notifications/${id}/read`)
}
import { useEffect, useRef } from "react";
import { createNotification } from "../notifications/models/notification.model";
import { useNotifications } from "../notifications/useNotifications";
import { useNavigate } from "react-router";
export function useNotificationSocket(userId) {
  console.log("SOCKET CHECKING: ", userId);
  console.log("SOCKET CHECKING URL: ", import.meta.env.VITE_API_WEB_SOCKET);

  const socketRef = useRef(null);
  const { addNotification } = useNotifications();
  const navigate = useNavigate();
  useEffect(() => {
    if (!userId) return;

    const socket = new WebSocket(
      `ws://${
        import.meta.env.VITE_API_WEB_SOCKET
      }/ws/notifications?userId=${userId}`
    );

    socket.onopen = () => {
      console.log("🔌 WebSocket connected");
    };

    socket.onmessage = (event) => {
      const data = JSON.parse(event.data);

      switch (data.type) {
        case "RESERVATION_EXPIRED":
          // show expired notification
          addNotification(
            createNotification({
              type: "warning",
              title: "Reservation Expired",
              message: `${data.parkingLotName} reservation expired`,
            })
          );
          // reservation expiry -> change the route to parking
          navigate("notification");
          break;

        case "SLOT_ASSIGNED":
          // show slot assigned
          addNotification(
            createNotification({
              type: "success",
              title: "Slot Assigned",
              message: `Slot assigned at ${data.parkingLotName}`,
            })
          );
          break;

        case "SESSION_STARTED":
          // show session started
          console.log("session: ", data.type)
          addNotification(
            createNotification({
              type: "success",
              title: "Session Started",
              message: `Your Parking session has started at ${data.parkingLotName} on slotNumber ${data.slotId}`,
            })
          );
          navigate("notification");
          break;

        case "SESSION_REMINDER":
          // reminder UI
          console.log("session: ", data.type)
          addNotification(
            createNotification({
              type: "success",
              title: "Session Reminder",
              message: `Your Parking session is going to expire at ${data.parkingLotName} on slotNumber ${data.slotId}`,
            })
          );
          navigate("notification");
          break;

        case "SESSION_ENDED":
          addNotification(
            createNotification({
              type: "success",
              title: "Session Ended",
              message: `Your Parking Session Ended at ${data.parkingLotName} on slotNumber ${data.slotId}`,
            })
          );
          navigate("notification");
          // session ended UI
          console.log("session: ", data.type)
          break;
      }

      console.log("🔔 Notification received", data);
    };

    socket.onerror = (err) => {
      console.error("❌ WebSocket error", err);
    };

    socket.onclose = () => {
      console.log("🔌 WebSocket disconnected");
    };

    socketRef.current = socket;

    return () => socket.close();
  }, [userId]);
}

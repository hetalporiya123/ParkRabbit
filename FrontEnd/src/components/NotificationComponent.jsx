import { updateNotificationStatus } from "../api/notifications";
import { useNotifications } from "../notifications/useNotifications";
import {
  Card,
  CardContent,
  Typography,
  Box,
  Chip,
} from "@mui/material";
import { formatCreatedAt } from "../services/dateTime.service";

export default function NotificationComponent() {
  
  const { notifications, markAsRead } = useNotifications();
const handleNotificationClick = async (n) => {
  if (n.read) return;

  try {
    const id= n.id
    await updateNotificationStatus(id); // backend
    console.log(n)
    markAsRead(n.id);                     // frontend
  } catch (err) {
    console.error("Failed to update notification", err);
  }
};

  if (notifications.length === 0) {
    return (
      <Typography color="text.secondary">
        No notifications yet
      </Typography>
    );
  }

  return (
    <Box sx={{ display: "flex", flexDirection: "column", gap: 2 }}>
      {notifications.map((n) => (
        <Card
          key={n.id}
          sx={{
            backgroundColor: n.read ? "inherit" : "rgba(25,118,210,0.08)",
          }}
          onClick={() => handleNotificationClick(n)}
        >
          <CardContent>
            <Box sx={{ display: "flex", justifyContent: "space-between" }}>
              <Typography variant="h6">{n.title}</Typography>
              <Chip
                size="small"
                label={n.type}
                color={getChipColor(n.type)}
              />
            </Box>

            <Typography variant="body2" color="text.secondary" sx={{ mt: 1 }}>
              {n.message}
            </Typography>

            <Typography variant="caption" color="text.secondary">
              {formatCreatedAt(n.createdAt)}
            </Typography>
          </CardContent>
        </Card>
      ))}
    </Box>
  );
}

function getChipColor(type) {
  switch (type) {
    case "success":
      return "success";
    case "warning":
      return "warning";
    case "error":
      return "error";
    default:
      return "info";
  }
}

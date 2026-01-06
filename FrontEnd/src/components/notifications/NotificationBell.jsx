import { useState } from "react";
import { useNavigate } from "react-router";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import ListItemText from "@mui/material/ListItemText";
import Divider from "@mui/material/Divider";
import IconButton from "@mui/material/IconButton";
import Badge from "@mui/material/Badge";
import NotificationsIcon from "@mui/icons-material/Notifications";

import { useNotifications } from "../../notifications/useNotifications";
import { updateNotificationStatus } from "../../api/notifications";

export default function NotificationBell() {
  // 🔹 local UI state (belongs here)
  const [anchorEl, setAnchorEl] = useState(null);
  const openBell = Boolean(anchorEl);
  const handleNotificationClick = async (n) => {
    if (n.read) return;

    try {
      await updateNotificationStatus(n.id);
      markAsRead(n.id);
    } catch (err) {
      console.error("Failed to update notification", err);
    }
  };
  // 🔹 global notification state
  const { notifications, markAsRead } = useNotifications();

  const navigate = useNavigate();

  const unreadCount = notifications.filter((n) => !n.read).length;

  const handleBellClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleBellClose = () => {
    setAnchorEl(null);
  };

  return (
    <>
      {/*  Bell button */}
      <IconButton color="inherit" onClick={handleBellClick}>
        <Badge
          badgeContent={unreadCount}
          color="error"
          invisible={unreadCount === 0}
        >
          <NotificationsIcon />
        </Badge>
      </IconButton>

      {/*  Dropdown */}
      <Menu
        anchorEl={anchorEl}
        open={openBell}
        onClose={handleBellClose}
        PaperProps={{ sx: { width: 320 } }}
      >
        {notifications.length === 0 && (
          <MenuItem disabled>
            <ListItemText primary="No notifications" />
          </MenuItem>
        )}

        {notifications.slice(0, 5).map((n) => (
          <MenuItem
            key={n.id}
            onClick={() => {
              handleNotificationClick(n)
              handleBellClose();
            }}
            sx={{
              backgroundColor: n.read
                ? "inherit"
                : "rgba(25,118,210,0.08)",
            }}
          >
            <ListItemText primary={n.title} secondary={n.message} />
          </MenuItem>
        ))}

        <Divider />

        <MenuItem
          onClick={() => {
            handleBellClose();
            navigate("/dashboard/notification");
          }}
        >
          <ListItemText
            primary="View all notifications"
            primaryTypographyProps={{ fontWeight: "bold" }}
          />
        </MenuItem>
      </Menu>
    </>
  );
}

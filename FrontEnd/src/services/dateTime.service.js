import { formatInTimeZone } from "date-fns-tz";

export const formatCreatedAt = (createdAt) => {
  if (!createdAt) return "";

  // If backend sends LocalDateTime array
  if (Array.isArray(createdAt)) {
    const [y, m, d, h, min, s] = createdAt;
    const utcDate = new Date(Date.UTC(y, m - 1, d, h, min, s));

    return formatInTimeZone(
      utcDate,
      "Europe/Berlin",
      "dd.MM.yyyy, HH:mm"
    );
  }

  // ISO string (with or without Z)
  if (typeof createdAt === "string") {
    const iso = createdAt.endsWith("Z")
      ? createdAt
      : createdAt + "Z"; // 🔒 force UTC

    return formatInTimeZone(
      iso,
      "Europe/Berlin",
      "dd.MM.yyyy, HH:mm"
    );
  }

  return "";
};

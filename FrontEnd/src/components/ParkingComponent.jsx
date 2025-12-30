import GenericCardComponent from "./GenericCardComponent";
import parkingAnimation from "../assets/parkingAnimation.json";
import parkingReserved from "../assets/parkingReserved.json";

import { parkingLots, reserveParkingSlot } from "../api/parkingLots.js";
import { useState } from "react";
import { formatTime } from "../services/dateTime.service.js";
export default function ParkingComponent() {
  const [parking, setParking] = useState([]);
  const [reservation, setReservation] = useState(null);
  const handleParkingLots = async () => {
    try {
      const response = await parkingLots();
      setParking(response);
      console.log(response);
    } catch (error) {
      console.error("Login failed", error);
    }
  };
  const handleCreateReservation = async (parkingLotId) => {
    try {
      const response = await reserveParkingSlot(parkingLotId);
      setReservation(response);
    } catch (error) {
      console.log(error);
    }
  };

  const renderView = () => {
    if (reservation) {
      return (
        <div className="flex gap-2 justify-baseline flex-wrap">
          <GenericCardComponent
            key={reservation.id}
            title={`${reservation.parkingLotAddress} Parking Lot ${reservation.parkingLotId}`}
            description={`Parking Slot No. • ${reservation.slotId} Reserved at • ${formatTime(reservation.reservedAt)} Expires at • ${formatTime(reservation.expiresAt)} `}
            animationData={parkingReserved}
          />
        </div>
      );
    }
    if (parking.length > 0) {
      return (
        <div className="flex gap-2 justify-baseline flex-wrap">
          {parking.map((lot) => (
            <GenericCardComponent
              key={lot.id}
              title={lot.name}
              description={`Euro ${lot.hourlyRate}/hour • ${lot.freeSlots} Available slots • ${lot.occupiedSlots} Occupied slots • ${lot.reservedSlots} Reserved slots  • ${lot.totalSlots} Total slots`}
              buttonText="Reserve"
              onButtonClick={() => handleCreateReservation(lot.id)}
              animationData={parkingAnimation}
            />
          ))}
        </div>
      );
    } else {
      return (
        <GenericCardComponent
          title="Car Parking"
          description="Use Park Rabbit Parking feature which allows you to park you car in the nearest parking lot."
          buttonText="Park"
          onButtonClick={handleParkingLots}
          animationData={parkingAnimation}
        />
      );
    }
  };
  return renderView();
}

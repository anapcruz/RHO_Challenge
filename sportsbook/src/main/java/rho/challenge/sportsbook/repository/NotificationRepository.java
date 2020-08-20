package rho.challenge.sportsbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rho.challenge.sportsbook.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}

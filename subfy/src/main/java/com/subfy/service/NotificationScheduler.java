package com.subfy.service;

import com.subfy.entity.Subscription;
import com.subfy.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@EnableScheduling
public class NotificationScheduler {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EmailService emailService;

    // Roda todo dia às 08:00 da manhã
    @Scheduled(cron = "0 0 8 * * *")
    public void verificarVencimentos() {
        System.out.println(" Verificando vencimentos para envio de email...");

        LocalDate hoje = LocalDate.now();
        int diaHoje    = hoje.getDayOfMonth();
        int diasNoMes  = hoje.getMonth().length(hoje.isLeapYear());

        List<Subscription> todas = subscriptionRepository.findAll();

        for (Subscription sub : todas) {

            // Ignora canceladas
            if (sub.getStatus() == null || sub.getStatus().equals("CANCELADA")) continue;

            // Ignora sem email
            if (sub.getUserEmail() == null || sub.getUserEmail().isEmpty()) continue;

            int diaBilling = sub.getBillingDate();
            int diff       = diaBilling - diaHoje;
            if (diff < 0) diff += diasNoMes;

            // Envia nos dias: vence hoje (0), amanhã (1) ou em 3 dias (3)
            if (diff == 0 || diff == 1 || diff == 3) {
                emailService.enviarAlertaVencimento(
                        sub.getUserEmail(),
                        sub.getServiceName(),
                        diff,
                        sub.getPrice()
                );
            }
        }

        System.out.println(" Verificação de vencimentos concluída.");
    }
}
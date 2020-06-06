package com.example.TaxiApp.Service.ServicesImpl;

import com.example.TaxiApp.DTO.CustomerPlan.CustomerPlanSearchDto;
import com.example.TaxiApp.DTO.DriverPlan.DriverPlanUpdateSeatsDto;
import com.example.TaxiApp.DTO.error.DriverMatchException;
import com.example.TaxiApp.Entity.Driver;
import com.example.TaxiApp.Entity.Order.DriverPlan;
import com.example.TaxiApp.Entity.Order.QDriverPlan;
import com.example.TaxiApp.Mapper.DriverPlansMapper;
import com.example.TaxiApp.Repository.DriverPlanRepository;
import com.example.TaxiApp.Service.DriverPlansService;
import com.example.TaxiApp.Service.DriverService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class
DriverPlansServiceImpl implements DriverPlansService {

    private final DriverPlanRepository driverPlanRepository;
    private final DriverPlansMapper driverPlansMapper;
    private final DriverService driverService;

    public DriverPlansServiceImpl(DriverPlanRepository driverPlanRepository,
                                  DriverPlansMapper driverPlansMapper, DriverService driverService) {
        this.driverPlanRepository = driverPlanRepository;
        this.driverPlansMapper = driverPlansMapper;

        this.driverService = driverService;
    }

    @Override
    public DriverPlan save(DriverPlan dto) {
        DriverPlan plan = driverPlanRepository.save(dto);
        return plan;
    }


    @Override
    public Page<DriverPlan> getCloseFromLocations(CustomerPlanSearchDto customerPlanSearchDto) {
        List<Double> ans = getMinMaxPoints(customerPlanSearchDto.getFromLat(),customerPlanSearchDto.getFromLon());
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(QDriverPlan.driverPlan.fromLat.between(ans.get(0),ans.get(1)));
        predicate.and(QDriverPlan.driverPlan.fromLon.between(ans.get(2),ans.get(3)));
        List<Double> ans1 = getMinMaxPoints(customerPlanSearchDto.getToLat(),customerPlanSearchDto.getToLon());
        predicate.and(QDriverPlan.driverPlan.toLat.between(ans1.get(0),ans1.get(1)));
        predicate.and(QDriverPlan.driverPlan.toLon.between(ans1.get(2),ans1.get(3)));
        predicate.and(QDriverPlan.driverPlan.availableSeats.goe(customerPlanSearchDto.getNumOfPassengers()));
        predicate.and(QDriverPlan.driverPlan.time.between(customerPlanSearchDto.getTimeFrom(),customerPlanSearchDto.getTimeTo()));
        PageRequest page = PageRequest.of(0,10);
        return driverPlanRepository.findAll(predicate,page);
    }


    private List<Double> getMinMaxPoints(double lat, double lon) {
        List<Double> a = new ArrayList<>();
        double minLat = lat - Math.toDegrees(1000.0/6371);
        double maxLat = lat + Math.toDegrees(1000.0/6371);
        a.add(minLat);
        a.add(maxLat);
        double minLon = lon - Math.toDegrees(Math.asin(1000.0/6371)/Math.cos(Math.toRadians(lat)));
        a.add(minLon);
        double maxLon = lon + Math.toDegrees(Math.asin(1000.0/6371)/ Math.cos(Math.toRadians(lat)));
        a.add(maxLon);
        return a;
    }

    @Override
    public DriverPlan findById(Long driverPlanId) {
        DriverPlan ans = driverPlanRepository.findById(driverPlanId).
                orElseThrow(()->new EntityNotFoundException("There is no driver plan with id " + driverPlanId));
        return ans;
    }
    @Override
    public DriverPlan updateSeats(DriverPlanUpdateSeatsDto dto) {
        DriverPlan updated = findById(dto.getDriverPlanId());
        updated.setNumberOfPassengers(updated.getNumberOfPassengers()+dto.getSeats());
        updated.setAvailableSeats(updated.getAvailableSeats()-dto.getSeats());
        updated.setFilled(updated.getAvailableSeats() == 0? true:false);
        return driverPlanRepository.save(updated);
    }

    @Override
    public List<DriverPlan> findAllByDriverId(Driver id) {
        return driverPlanRepository.findAllByDriver(id);
    }

    @Override
    public DriverPlan delete(Long driverPlanId, Long id) throws DriverMatchException {
        DriverPlan delete = driverPlanRepository.findById(driverPlanId).
                orElseThrow(()->new EntityNotFoundException());
        if (id != delete.getDriver().getId())
            throw new DriverMatchException(id,driverPlanId);
        driverPlanRepository.delete(delete);
        return delete;
    }
}

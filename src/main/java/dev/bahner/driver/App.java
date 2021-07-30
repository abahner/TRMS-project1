package dev.bahner.driver;

import dev.bahner.controllers.BenCoordController;
import dev.bahner.controllers.CourseController;
import dev.bahner.controllers.EmployeeController;
import dev.bahner.controllers.RFormController;
import dev.bahner.controllers.SupervisorController;
import dev.bahner.repos.BenCoordImpl;
import dev.bahner.repos.BenCoordRepo;
import dev.bahner.repos.CourseRepo;
import dev.bahner.repos.CourseRepoImpl;
import dev.bahner.repos.EmployeeRepo;
import dev.bahner.repos.EmployeeRepoImpl;
import dev.bahner.repos.RFormImpl;
import dev.bahner.repos.ReimburseFormRepo;
import dev.bahner.repos.SupervisorRepo;
import dev.bahner.repos.SupervisorRepoImpl;
import dev.bahner.services.BenCoordService;
import dev.bahner.services.BenCoordServiceImpl;
import dev.bahner.services.CourseService;
import dev.bahner.services.CourseServiceImpl;
import dev.bahner.services.EmployeeService;
import dev.bahner.services.EmployeeServiceImpl;
import dev.bahner.services.RFormService;
import dev.bahner.services.RFormServiceImpl;
import dev.bahner.services.SupervisorService;
import dev.bahner.services.SupervisorServiceImpl;
import io.javalin.Javalin;

public class App {

	public static void main(String[] args) {

		Javalin app = Javalin.create(config -> config.enableCorsForAllOrigins());
		establishRoutes(app);
		app.start(7000);

	}

	private static void establishRoutes(Javalin app) {

		EmployeeRepo er = new EmployeeRepoImpl();
		EmployeeService es = new EmployeeServiceImpl(er);
		EmployeeController ec = new EmployeeController(es);
		
		CourseRepo cr = new CourseRepoImpl();
		CourseService cs = new CourseServiceImpl(cr);
		CourseController cc = new CourseController(cs);
		
		ReimburseFormRepo rr = new RFormImpl();
		RFormService rs = new RFormServiceImpl(rr);
		RFormController rc = new RFormController(rs);
		
		SupervisorRepo sr = new SupervisorRepoImpl();
		SupervisorService ss = new SupervisorServiceImpl(sr);
		SupervisorController sc = new SupervisorController(ss);
		
		BenCoordRepo br = new BenCoordImpl();
		BenCoordService bs = new BenCoordServiceImpl(br);
		BenCoordController bc = new BenCoordController(bs);

		// Force hibernate to load on start
		er.getEmployee(0);
		
		// Home page
		app.get("/", (ctx) -> ctx.result("Tuition Reimbursement Management System!"));
		
		// Employee Routes
		app.get("employees", ec.getAllEmployees);
		app.get("employees/:id", ec.getEmployeeById);
		app.post("employees", ec.addEmployee);
		app.put("employees/:id", ec.updateEmployee);
		app.delete("employees/:id", ec.deleteEmployee);

		// Course Routes
		app.get("courses", cc.getAllCourses);
		app.get("courses/:id", cc.getCourseById);
		app.post("courses", cc.addCourse);
		app.put("courses/:id", cc.updateCourse);
		app.delete("courses/:id", cc.deleteCourse);
		
		// Reimburse Form Routes
		app.get("rforms", rc.getAllRForms);
		app.get("rforms/:id", rc.getRFormById);
		app.post("rforms", rc.addRForm);
		app.put("rforms/:id", rc.updateRForm);
		app.delete("rforms/:id", rc.deleteRForm);
		
		// Supervisor Routes
		app.get("supervisors", sc.getAllSupervisors);
		app.get("supervisors/:id", sc.getSupervisorById);
		app.post("supervisors", sc.addSupervisor);
		app.put("supervisors/:id", sc.updateSupervisor);
		app.delete("supervisors/:id", sc.deleteSupervisor);
		
		// Benefits Coordinator Routes
		app.get("bencoords", bc.getAllBenCoords);
		app.get("bencoords/:id", bc.getBenCoordById);
		app.post("bencoords", bc.addBenCoord);
		app.put("bencoords/:id", bc.updateBenCoord);
		app.delete("bencoords/:id", bc.deleteBenCoord);
	}

}

package main;

import adapters.cli.*;
import dao.EmpleadoDao;
import dao.LegajoDao;
import dao.MySQLEmpleadoDao;
import dao.MySQLLegajoDao;
import services.EmpleadoService;
import services.EmpleadoServiceImpl;
import services.LegajoService;
import services.LegajoServiceImpl;
import utils.logger.Logger;
import utils.logger.LoggerImpl;
import utils.reader.InputReader;
import utils.reader.InputReaderImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Logger logger = new LoggerImpl(System.out);
        InputReader inputReader = new InputReaderImpl(scanner, logger);

        EmpleadoDao empleadoDao = new MySQLEmpleadoDao();
        LegajoDao legajoDao = new MySQLLegajoDao();

        EmpleadoService empleadoService = new EmpleadoServiceImpl(empleadoDao);
        LegajoService legajoService = new LegajoServiceImpl(legajoDao);
        EmpleadoCliInterface empleadoCli = new EmpleadoCliInterfaceImpl(empleadoService, inputReader, logger);
        LegajoCliInterface legajoCli = new LegajoCliInterfaceImpl(legajoService, inputReader, logger);

        AppMenuCliInterface menuCli = new AppMenuCliInterfaceImpl(empleadoCli, legajoCli, inputReader, logger);
        menuCli.main();
    }
}

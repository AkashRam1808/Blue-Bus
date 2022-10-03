package com.bluebus.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bluebus.model.Bluebus;
import com.bluebus.util.BusMapper;
import com.bluebus.util.DbConnection;
import com.bluebus.util.IRowMapper;
import com.bluebus.util.Queries;

public class BluebusDaoImpl implements IBluebusDao {
	PreparedStatement statement = null;
	ResultSet resultset = null;
	IRowMapper mapper = new BusMapper();

	@Override
	public void addBus(Bluebus bus) {

		Date date = Date.valueOf(bus.getStartDate());
		Timestamp startTimeTimestamp = Timestamp.valueOf(bus.getStartTime());
		Connection connection = DbConnection.openConnection();

		try {
			statement = connection.prepareStatement(Queries.ADDQUERY);
			statement.setInt(1, bus.getBusNumber());
			statement.setString(2, bus.getBusName());
			statement.setDate(3, date);
			statement.setString(4, bus.getCategory());
			statement.setString(5, bus.getType());
			statement.setDouble(6, bus.getCost());
			statement.setString(7, bus.getSource());
			statement.setString(8, bus.getDestination());
			statement.setInt(9, bus.getSeatsAvailable());
			statement.setTimestamp(10, startTimeTimestamp);
			statement.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			if (statement != null) {
				try {
					statement.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			DbConnection.closeConnection();
		}

	}

	@Override
	public void updateBus(int busNumber, double cost) {
		Connection connection = DbConnection.openConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(Queries.UPDATEQUERY);
			statement.setDouble(1, cost);
			statement.setInt(2, busNumber);
			statement.execute();
			System.out.println("Updated successfully!");
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			if (statement != null) {
				try {
					statement.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			DbConnection.closeConnection();
		}

	}

	@Override
	public Bluebus getByNumber(int busNumber) {
		Bluebus bus = null;

		Connection connection = DbConnection.openConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(Queries.QUERYBYID);
			statement.setInt(1, busNumber);
			resultset = statement.executeQuery();
			while (resultset.next()) {

				Date startDate = resultset.getDate(3);
				LocalDate localStartDate = startDate.toLocalDate();
				Timestamp startTime = resultset.getTimestamp(10);
				LocalDateTime localDateTime = startTime.toLocalDateTime();
				bus = new Bluebus();
				bus.setBusNumber(resultset.getInt(1));
				bus.setBusName(resultset.getString(2));
				bus.setStartDate(localStartDate);
				bus.setCategory(resultset.getString(4));
				bus.setType(resultset.getString(5));
				bus.setCost(resultset.getDouble(6));
				bus.setSource(resultset.getString(7));
				bus.setDestination(resultset.getString(8));
				bus.setSeatsAvailable(resultset.getInt(9));
				bus.setStartTime(localDateTime);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (statement != null && resultset != null) {

				try {
					resultset.close();
					statement.close();

				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			DbConnection.closeConnection();
		}

		return bus;
	}

	@Override
	public void deleteBus(int busNumber) {
		Connection connection = DbConnection.openConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(Queries.DELETEQUERY);
			statement.setInt(1, busNumber);
			statement.execute();
			System.out.println("Deleted successfully!");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			DbConnection.closeConnection();
		}

	}

	@Override
	public List<Bluebus> findAll(String source, String destination) {
		Connection connection = DbConnection.openConnection();
		
		List<Bluebus> buses = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(Queries.QUERYBYSOURCE);
			statement.setString(1, source);
			statement.setString(2, destination);
			ResultSet resultset = statement.executeQuery();
			buses = mapper.rowMap(resultset);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null && resultset != null) {

				try {
					resultset.close();
					statement.close();

				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			DbConnection.closeConnection();
		}
		return buses;
	}

	@Override
	public List<Bluebus> findByLessFare(String source, String destination, double cost) {
		Connection connection = DbConnection.openConnection();
		
		List<Bluebus> buses = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(Queries.QUERYBYCOST);
			statement.setString(1, source);
			statement.setString(2, destination);
			statement.setDouble(3, cost);
			ResultSet resultset = statement.executeQuery();
			buses = mapper.rowMap(resultset);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null && resultset != null) {

				try {
					resultset.close();
					statement.close();

				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			DbConnection.closeConnection();
		}
		return buses;
	}

	@Override
	public List<Bluebus> findbyCategory(String source, String destination, String category) {
		Connection connection = DbConnection.openConnection();
		
		List<Bluebus> buses = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(Queries.QUERYBYCATEGORY);
			statement.setString(1, source);
			statement.setString(2, destination);
			statement.setString(3, category);
			ResultSet resultset = statement.executeQuery();
			buses = mapper.rowMap(resultset);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null && resultset != null) {

				try {
					resultset.close();
					statement.close();

				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			DbConnection.closeConnection();
		}
		return buses;
	}

	@Override
	public List<Bluebus> findbyType(String source, String destination, String type) {
		Connection connection = DbConnection.openConnection();
		List<Bluebus> buses = new ArrayList<>();

		try {
			PreparedStatement statement = connection.prepareStatement(Queries.QUERYBYTYPE);
			statement.setString(1, source);
			statement.setString(2, destination);
			statement.setString(3, type);
			ResultSet resultset = statement.executeQuery();
			buses = mapper.rowMap(resultset);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null && resultset != null) {

				try {
					resultset.close();
					statement.close();

				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			DbConnection.closeConnection();
		}
		return buses;
	}

	@Override
	public List<Bluebus> findbyStartTime(String source, String destination, LocalDateTime startTime) {
		Connection connection = DbConnection.openConnection();
		List<Bluebus> buses = new ArrayList<>();
		Timestamp startTimeTimestamp = Timestamp.valueOf(startTime);
		try {
			PreparedStatement statement = connection.prepareStatement(Queries.QUERYBYTIME);
			statement.setString(1, source);
			statement.setString(2, destination);
			statement.setTimestamp(3, startTimeTimestamp);
			ResultSet resultset = statement.executeQuery();
			buses = mapper.rowMap(resultset);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null && resultset != null) {

				try {
					resultset.close();
					statement.close();

				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			DbConnection.closeConnection();
		}
		return buses;
	}

}

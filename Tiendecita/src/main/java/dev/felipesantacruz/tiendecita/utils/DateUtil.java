package dev.felipesantacruz.tiendecita.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil
{
	public static String getTodayInShrotFormat()
	{
		return getShortDateFormat(LocalDate.now());
	}
	
	public static String getShortDateFormat(LocalDate d)
	{
		return d.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
	}
	
	public static LocalDate toLocalDate(String stringDate) throws DateTimeParseException
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[d/M[M]/yyyy][d/M[M]/yy][d-M[M]-yyyy][d-M[M]-yy]");
		return LocalDate.parse(stringDate, formatter);
	}

	public static Optional<LocalDate> extractDateFrom(String text) throws DateTimeParseException
	{
		String regex = "((.*\\D+)|(^))(?<date>[0-3]?[0-9][/-][0-3]?[0-9][/-](?:[0-9]{2})?[0-9]{2})((.*)|($))";
		Matcher matcher = createMatcher(regex, text);
		if (matcher.matches())
		{
			String stringDate = matcher.group("date");
			return Optional.of(toLocalDate(stringDate));
		}
		return Optional.empty();
		
	}

	private static Matcher createMatcher(String regex, String text)
	{
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		return matcher;
	}
}

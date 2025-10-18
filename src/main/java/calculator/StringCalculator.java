package calculator;

public class StringCalculator {

    public int add(String text){
        // 빈 문자열 입력 시 0 반환
        if (text == null || text.isEmpty()){
            return 0;
        }

        String[] numbers = parseNumbers(text);
        return sumNumbers(numbers);
    }

    // 파싱 로직
    private String[] parseNumbers(String text){
        // 커스텀 구분자 형식일 경우
        if (text.startsWith("//")){
            int idx = text.indexOf("\\n");
            if (idx == -1){
                throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다.");
            }
            String customSeparator = text.substring(2, idx);
            String numString = text.substring(idx+2);
            return numString.split("\\Q" + customSeparator + "\\E");
        }

        // 기본 구분자 처리 로직
        return text.split("[,:]");
    }

    // 덧셈 로직
    private int sumNumbers(String[] numbers){
        int sum = 0;
        for (String number : numbers) {
            if (!number.trim().isEmpty()){
                try{
                    int num = Integer.parseInt(number.trim());
                    if (num < 0){
                        throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
                    }
                    sum += num;
                } catch (NumberFormatException e){
                    throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다.");
                }
            }
        }
        return sum;
    }
}
